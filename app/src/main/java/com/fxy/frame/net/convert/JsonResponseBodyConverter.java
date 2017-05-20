package com.fxy.frame.net.convert;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Description: ResponseBody to T
 */
final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    JsonResponseBodyConverter() {
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return (T) value.string();
    }
}
