package codewizards.com.ua.gallery.sections.internet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.model.net.Item;
import codewizards.com.ua.gallery.mvp.PresenterCache;
import codewizards.com.ua.gallery.mvp.PresenterFactory;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;
import codewizards.com.ua.gallery.util.Const;
import codewizards.com.ua.gallery.util.IntentHelper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by User on 27.02.2017.
 */

public class InternetFragment extends BaseFragment implements InternetImagesAdapter.OnNetImageActionListener {

    RecyclerView rvNetImages;
    EditText etSearchQuery;
    ProgressBar progressBar;

    PresenterFactory<InternetFragmentPresenter> factory = InternetFragmentPresenter::new;
    InternetFragmentPresenter presenter;
    InternetImagesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_internet, container, false);
        rvNetImages = (RecyclerView) root.findViewById(R.id.rvNetImages);
        etSearchQuery = (EditText) root.findViewById(R.id.etSearchQuery);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = PresenterCache.get().getPresenter(Const.PRESENTER_INTERNET_FRAGMENT, factory);
        presenter.setView(this);
        initRecyclerView();
        initEditText();
        presenter.update();
    }

    public void initRecyclerView() {
        adapter = new InternetImagesAdapter(this);
        rvNetImages.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvNetImages.setAdapter(adapter);
    }

    public void initEditText() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                etSearchQuery.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void afterTextChanged(Editable s) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        subscriber.onNext(s.toString());
                    }
                });
            }
        }).debounce(1000, TimeUnit.MILLISECONDS)
                .filter(s -> !s.isEmpty())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    presenter.search(s);
                });

    }

    public void onImagesUpdated(List<Item> images) {
        adapter.updateData(images);
        adapter.notifyDataSetChanged();
    }


    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void finishLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onImageSelected(Item item) {
        IntentHelper.openPictureActivity(getActivity(), item.getLink());
    }

    @Override
    public void onDownloadButtonClicked(Item item) {
        IntentHelper.startImageLoaderService(getActivity(), item.getLink());
//        presenter.loadImage(item);
    }
}
