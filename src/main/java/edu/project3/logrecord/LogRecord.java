package edu.project3.logrecord;

import edu.project3.coderesponse.CodeResponse;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRecord {
    @SuppressWarnings("LineLength")
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(.*) - (.*) \\[(.*)] \"(\\w+) (.*)\" (\\d{3}) (\\d+) \"(.+)\" \"(.*)\"");
    private static final DateTimeFormatter DATA_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    private final String remoteAddress;
    private final String remoteUser;
    private final OffsetDateTime timeLocal;
    private final String request;
    private final int status;
    private int bodyBytesSent;
    private final String httpReferer;
    private final String httpUserAgent;
    private final String codeResponse;
    private final String source;

    @SuppressWarnings("MagicNumber")
    public LogRecord(final String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (matcher.matches()) {
            remoteAddress = matcher.group(1);
            remoteUser = matcher.group(2);
            timeLocal = OffsetDateTime.parse(matcher.group(3), DATA_FORMATTER);
            request = matcher.group(4);
            source = matcher.group(5);
            status = Integer.parseInt(matcher.group(6));
            codeResponse = CodeResponse.getDescriptionByCode(status);
            httpReferer = matcher.group(8);
            httpUserAgent = matcher.group(9);

            try {
                bodyBytesSent = Integer.parseInt(matcher.group(7));
            } catch (NumberFormatException e) {
                bodyBytesSent = 0;
            }
        } else {
            throw new IllegalArgumentException(
                "Invalid log format: " + logLine
            );
        }
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public OffsetDateTime getTimeLocal() {
        return timeLocal;
    }

    public String getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public int getBodyBytesSent() {
        return bodyBytesSent;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public String getCodeResponse() {
        return codeResponse;
    }

    public String getSource() {
        return source;
    }
}
