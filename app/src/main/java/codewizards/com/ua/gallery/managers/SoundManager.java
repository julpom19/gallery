package codewizards.com.ua.gallery.managers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;

import java.io.IOException;

import codewizards.com.ua.gallery.GalleryApp;
import codewizards.com.ua.gallery.util.FileUtils;
import codewizards.com.ua.gallery.util.Logger;

/**
 * Created by User on 15.03.2017.
 */

public class SoundManager {
    private static final String SOUNDS_FOLDER = "sounds";
    private Logger logger = Logger.getLogger(this.getClass());
    private static final String SPLASH_SOUND_NAME = "splash.mp3";
    private static final String LIKE_SOUND_NAME = "like.wav";

    private MediaPlayer player;
    private Context context;
    private AudioManager audioManager;
    private String playingNowSoundName;
    private boolean isSoundsEnabledInPreferences = true;
    private boolean isPlaying;
    private boolean isPaused;

    private static SoundManager instance = new SoundManager();

    private SoundManager() {
        this.context = GalleryApp.getAppContext();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public static SoundManager get() {
        return instance;
    }

    public void playSplashSound() {
        if(PreferencesManager.isSoundFxEnabled()) {
            Logger.d(this.getClass(), "playSplashSound() " + "");
            playSound(SPLASH_SOUND_NAME, null);
        }
    }

    public void playLikeSound() {
        if(PreferencesManager.isSoundFxEnabled()) {
            Logger.d(this.getClass(), "playLikeSound() " + "");
            playSound(LIKE_SOUND_NAME, null);
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isPaused() {
        return isPaused;
    }

    private boolean playSound(String soundName, MediaPlayer.OnCompletionListener onCompletionListener) {
        Logger.d(this.getClass(), "playSound() " + "soundName = [" + soundName + "], onCompletionListener = [" + onCompletionListener + "]");
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }
        if(isSoundsEnabledInPreferences && isScreenOn) {
            try {
                AssetFileDescriptor descriptor = FileUtils.getAssets().openFd(SOUNDS_FOLDER + FileUtils.PATH_DIV + soundName);
                initMediaPlayer(descriptor);
                player.prepareAsync();
                if (onCompletionListener != null) {
                    player.setOnCompletionListener(onCompletionListener);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                // FileUtils.close(descriptor); crash on Samsung S4 (java.lang.IncompatibleClassChangeError) // FIXME
            }
        } else {
            return false;
        }
    }

    private void stopPlaying() {
        if(player != null && (isPlaying || isPaused)) {
            logger.v("stop playing current sound");
            isPlaying = false;
            isPaused = false;
            player.release();
        } else {
            logger.w("Can't stop timer!");
        }
    }

    private void initMediaPlayer(AssetFileDescriptor descriptor) {
        audioManager.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        logger.v("initMediaPlayer()");
        stopPlaying();
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new PlayerOnPreparedListener());
        try {
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class PlayerOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
        }
    }

    private AudioManager.OnAudioFocusChangeListener focusChangeListener = focusChange -> {
        switch (focusChange) {
            case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) :
                logger.v("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                break;
            case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) :
                logger.v("AUDIOFOCUS_LOSS_TRANSIENT");
                //pauseHomePlaying();
                break;
            case (AudioManager.AUDIOFOCUS_LOSS) :
                logger.v("AUDIOFOCUS_LOSS");
                //stopPlaying();
                break;
            case (AudioManager.AUDIOFOCUS_GAIN) :
                logger.v("AUDIOFOCUS_GAIN");
                //processHomeSoundResumed();
                break;
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT: {
                logger.v("AUDIOFOCUS_GAIN_TRANSIENT");
                break;
            }
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE: {
                logger.v("AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE");
                break;
            }
            default: {
                logger.v("default focus: " + focusChange);
            }
        }
    };
}

