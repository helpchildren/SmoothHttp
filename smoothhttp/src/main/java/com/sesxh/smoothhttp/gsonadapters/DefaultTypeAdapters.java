package com.sesxh.smoothhttp.gsonadapters;


import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sesxh.smoothhttp.HttpGlobalConfig;
import com.sesxh.smoothhttp.SmoothHttp;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * @author LYH
 * @date 2021/1/13
 * @time 10:04
 * @desc GsonAdapter
 */


public class DefaultTypeAdapters {


    private DefaultTypeAdapters() {
        throw new UnsupportedOperationException();
    }

    public static final TypeAdapter<Boolean> BOOLEAN = new TypeAdapter<Boolean>() {
        @Override
        public Boolean read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (peek == JsonToken.NULL) {
                in.nextNull();
                // 返回配置的默认值
                if (config().getDefValues() != null) {
                    return config().getDefValues().getDefBoolean();
                }
            } else if (peek == JsonToken.STRING) {
                // support strings for compatibility with GSON 1.7
                return Boolean.parseBoolean(in.nextString());
            }
            return in.nextBoolean();
        }

        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefBoolean();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() {
        @Override
        public Boolean read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                // 返回配置的默认值
                if (config().getDefValues() != null) {
                    return config().getDefValues().getDefBoolean();
                }
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                return in.nextBoolean();
            }
            return Boolean.valueOf(in.nextString());
        }

        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefBoolean();
                }
            }
            out.value(value);
        }
    };


    public static final TypeAdapter<Integer> INTEGER = new TypeAdapter<Integer>() {
        @Override
        public Integer read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (jsonToken) {
                case NULL:
                    in.nextNull();
                    if (config().getDefValues() != null) {
                        return config().getDefValues().getDefInt();
                    }
                case NUMBER:
                    return in.nextInt();
                case STRING:
                    String str = in.nextString();
                    if (TextUtils.isEmpty(str)) {
                        if (config().getDefValues() != null) {
                            return config().getDefValues().getDefInt();
                        }
                    }
                    return Integer.valueOf(str);
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }

        @Override
        public void write(JsonWriter out, Integer value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefInt();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<Long> LONG = new TypeAdapter<Long>() {
        @Override
        public Long read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (jsonToken) {
                case NULL:
                    in.nextNull();
                    if (config().getDefValues() != null) {
                        return config().getDefValues().getDefLong();
                    }
                case NUMBER:
                    return in.nextLong();
                case STRING:
                    String str = in.nextString();
                    if (TextUtils.isEmpty(str)) {
                        if (config().getDefValues() != null) {
                            return config().getDefValues().getDefLong();
                        }
                    }
                    return Long.valueOf(str);
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }

        @Override
        public void write(JsonWriter out, Long value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefLong();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<Float> FLOAT = new TypeAdapter<Float>() {
        @Override
        public Float read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (jsonToken) {
                case NULL:
                    in.nextNull();
                    if (config().getDefValues() != null) {
                        return config().getDefValues().getDefFloat();
                    }
                case NUMBER:
                    return (float) in.nextDouble();
                case STRING:
                    String str = in.nextString();
                    if (TextUtils.isEmpty(str)) {
                        if (config().getDefValues() != null) {
                            return config().getDefValues().getDefFloat();
                        }
                    }
                    return Float.valueOf(str);
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }

        @Override
        public void write(JsonWriter out, Float value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefFloat();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<Double> DOUBLE = new TypeAdapter<Double>() {
        @Override
        public Double read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (jsonToken) {
                case NULL:
                    in.nextNull();
                    if (config().getDefValues() != null) {
                        return config().getDefValues().getDefDouble();
                    }
                case NUMBER:
                    return in.nextDouble();
                case STRING:
                    String str = in.nextString();
                    if (TextUtils.isEmpty(str)) {
                        if (config().getDefValues() != null) {
                            return config().getDefValues().getDefDouble();
                        }
                    }
                    return Double.valueOf(str);
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }

        @Override
        public void write(JsonWriter out, Double value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefDouble();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<Number> NUMBER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (jsonToken) {
                case NULL:
                    in.nextNull();
                    if (config().getDefValues() != null) {
                        return config().getDefValues().getDefDouble();
                    }
                case NUMBER:
                case STRING:
                    String str = in.nextString();
                    if (TextUtils.isEmpty(str)) {
                        if (config().getDefValues() != null) {
                            return config().getDefValues().getDefDouble();
                        }
                    }
                    return new LazilyParsedNumber(str);
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefDouble();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        @Override
        public String read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (peek == JsonToken.NULL) {
                in.nextNull();
                if (config().getDefValues() != null) {
                    return config().getDefValues().getDefString();
                }
            }
            if (peek == JsonToken.NUMBER) {
                double dbNum = in.nextDouble();
                if (dbNum > Long.MAX_VALUE) {
                    return String.valueOf(dbNum);
                }
                // 如果是整数
                if (dbNum == (long) dbNum) {
                    return String.valueOf((long) dbNum);
                } else {
                    return String.valueOf(dbNum);
                }
            }
            /* coerce booleans to strings for backwards compatibility */
            if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(in.nextBoolean());
            }
            return in.nextString();
        }

        @Override
        public void write(JsonWriter out, String value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = config().getDefValues().getDefString();
                }
            }
            out.value(value);
        }
    };

    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new TypeAdapter<BigDecimal>() {
        @Override
        public BigDecimal read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                if (config().getDefValues() != null) {
                    return new BigDecimal(config().getDefValues().getDefInt());
                }
            }
            try {
                return new BigDecimal(in.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public void write(JsonWriter out, BigDecimal value) throws IOException {
            if (value == null) {
                if (config().getDefValues() != null) {
                    value = new BigDecimal(config().getDefValues().getDefInt());
                }
            }
            out.value(value);
        }
    };

    private static HttpGlobalConfig config(){
        return SmoothHttp.getInstance().globalConfig();
    }
}
