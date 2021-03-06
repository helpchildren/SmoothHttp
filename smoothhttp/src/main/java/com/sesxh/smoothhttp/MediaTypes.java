package com.sesxh.smoothhttp;

import okhttp3.MediaType;

/**
 * @author LYH
 * @date 2021/1/5
 * @time 15:06
 * @desc 请求参数类型
 */

public class MediaTypes {
    public static final MediaType APPLICATION_JSON_TYPE = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType APPLICATION_OCTET_STREAM_TYPE = MediaType.parse("application/octet-stream");
    public static final MediaType APPLICATION_XML_TYPE = MediaType.parse("application/xml;charset=utf-8");
    public static final MediaType TEXT_HTML_TYPE = MediaType.parse("text/html;charset=utf-8");
    public static final MediaType TEXT_XML_TYPE = MediaType.parse("text/xml;charset=utf-8");
    public static final MediaType TEXT_PLAIN_TYPE = MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType IMAGE_TYPE = MediaType.parse("image/*");
}
