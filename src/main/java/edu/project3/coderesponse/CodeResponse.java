package edu.project3.coderesponse;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
public class CodeResponse {
    private static Map<Integer, String> responseMap = new HashMap<>();

    static {
        initializeResponseMap();
    }

    @SuppressWarnings("MagicNumber")
    private static void initializeResponseMap() {
        responseMap.put(100, "Continue");
        responseMap.put(101, "Switching Protocols");
        responseMap.put(102, "Processing");
        responseMap.put(200, "OK");
        responseMap.put(201, "Created");
        responseMap.put(202, "Accepted");
        responseMap.put(203, "Non-Authoritative Information");
        responseMap.put(204, "No Content");
        responseMap.put(205, "Reset Content");
        responseMap.put(206, "Partial Content");
        responseMap.put(207, "Multi-Status");
        responseMap.put(300, "Multiple Choices");
        responseMap.put(301, "Moved Permanently");
        responseMap.put(302, "Found");
        responseMap.put(303, "See Other");
        responseMap.put(304, "Not Modified");
        responseMap.put(305, "Use Proxy");
        responseMap.put(307, "Temporary Redirect");
        responseMap.put(400, "Bad Request");
        responseMap.put(401, "Unauthorized");
        responseMap.put(402, "Payment Required");
        responseMap.put(403, "Forbidden");
        responseMap.put(404, "Not Found");
        responseMap.put(405, "Method Not Allowed");
        responseMap.put(406, "Not Acceptable");
        responseMap.put(407, "Proxy Authentication Required");
        responseMap.put(408, "Request Timeout");
        responseMap.put(409, "Conflict");
        responseMap.put(410, "Gone");
        responseMap.put(411, "Length Required");
        responseMap.put(412, "Precondition Failed");
        responseMap.put(413, "Payload Too Large");
        responseMap.put(414, "URI Too Long");
        responseMap.put(415, "Unsupported Media Type");
        responseMap.put(416, "Range Not Satisfiable");
        responseMap.put(417, "Expectation Failed");
        responseMap.put(418, "I'm a teapot");
        responseMap.put(421, "Misdirected Request");
        responseMap.put(422, "Unprocessable Entity");
        responseMap.put(423, "Locked");
        responseMap.put(424, "Failed Dependency");
        responseMap.put(425, "Too Early");
        responseMap.put(426, "Upgrade Required");
        responseMap.put(428, "Precondition Required");
        responseMap.put(429, "Too Many Requests");
        responseMap.put(431, "Request Header Fields Too Large");
        responseMap.put(451, "Unavailable For Legal Reasons");
        responseMap.put(500, "Internal Server Error");
        responseMap.put(501, "Not Implemented");
        responseMap.put(502, "Bad Gateway");
        responseMap.put(503, "Service Unavailable");
        responseMap.put(504, "Gateway Timeout");
        responseMap.put(505, "HTTP Version Not Supported");
        responseMap.put(506, "Variant Also Negotiates");
        responseMap.put(507, "Insufficient Storage");
        responseMap.put(508, "Loop Detected");
        responseMap.put(510, "Not Extended");
        responseMap.put(511, "Network Authentication Required");
    }

    public static String getDescriptionByCode(final int code) {
        return responseMap.getOrDefault(code, "Unknown Code");
    }
}
