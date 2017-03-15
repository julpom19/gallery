package codewizards.com.ua.gallery.util;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {
    private static final int USERPIC_QUALITY_COEF = 70;

	public static byte[] toByteArray(InputStream inputStream) throws IOException {
		// this dynamically extends to take the bytes you read
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		// this is storage overwritten on each iteration with bytes
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		// we need to know how may bytes were read to write them to the
		// byteBuffer
		int len;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}

		// and then we can return your byte array.
		return byteBuffer.toByteArray();
	}

	public static String toString(InputStream is) throws IOException {
		StringBuilder result = new StringBuilder();
		InputStreamReader in = new InputStreamReader(is);

		// Load the results into a StringBuilder
		int read;
		char[] buff = new char[1024];
		while ((read = in.read(buff)) != -1) {
			result.append(buff, 0, read);
		}
		return result.toString();
	}

	private static byte[] getConvertedBitmap(Bitmap sourceBitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		sourceBitmap.compress(Bitmap.CompressFormat.JPEG, USERPIC_QUALITY_COEF, baos);
        return baos.toByteArray();
    }

    public static String getEncodedImage(Bitmap sourceBitmap) {
        return Base64.encodeToString(getConvertedBitmap(sourceBitmap), Base64.DEFAULT);
    }

	public static String toString(InputStream input, String encoding) throws IOException {
		StringWriter sw = new StringWriter();
		copy((InputStream)input, (Writer)sw, encoding);
		return sw.toString();
	}

	public static int copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4096];
		int count = 0;

		int n1;
		for(boolean n = false; -1 != (n1 = input.read(buffer)); count += n1) {
			output.write(buffer, 0, n1);
		}

		return count;
	}

	public static void copy(InputStream input, Writer output) throws IOException {
		InputStreamReader in = new InputStreamReader(input);
		copy((Reader)in, (Writer)output);
	}

	public static void copy(InputStream input, Writer output, String encoding) throws IOException {
		if(encoding == null) {
			copy(input, output);
		} else {
			InputStreamReader in = new InputStreamReader(input, encoding);
			copy((Reader)in, (Writer)output);
		}
	}

	public static int copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[4096];
		int count = 0;

		int n1;
		for(boolean n = false; -1 != (n1 = input.read(buffer)); count += n1) {
			output.write(buffer, 0, n1);
		}

		return count;
	}

	public static void copy(Reader input, OutputStream output) throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(output);
		copy((Reader)input, (Writer)out);
		out.flush();
	}
}