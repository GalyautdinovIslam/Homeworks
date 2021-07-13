package firstCourse.secondSemester.myURIclass;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyURI {
    private final String uri;

    private String scheme;
    private String schemeSpecificPart;
    private String authority;
    private String userInfo;
    private String host;
    private int port = -1;
    private String path;
    private String query;
    private String fragment;

    private final static int FOR_CHAR_SHIFT = 1;

    private void checkScheme() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[^[a-zA-Z][0-9]\\+\\-\\.]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.scheme);
        forTest = Pattern.compile("^[^a-zA-Z]");
        testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.scheme);
    }

    private void checkSSP() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[#]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.schemeSpecificPart);
    }

    private void checkFragment() {
        //ignored
    }

    private void checkAuthority() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[\\/\\?\\#]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.authority);
    }

    private void checkQuery() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[\\#\\[\\]]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.query);
    }

    private void checkPath() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[\\?\\#\\[\\]]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.path);
    }

    private void checkUserInfo() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[\\/\\?\\#\\[\\]@]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.userInfo);
    }

    private void checkPort() throws URISyntaxException {
        if (this.port < -1 || this.port > 65535) throw new URISyntaxException("Invalid syntax: ", "" + this.port);
    }

    private void checkHost() throws URISyntaxException {
        Pattern forTest = Pattern.compile("[\\/\\?\\#@]");
        Matcher testMatcher = forTest.matcher(this.scheme);
        if (testMatcher.find()) throw new URISyntaxException("Invalid syntax: ", this.host);
    }

    public MyURI(String str) throws URISyntaxException {
        this.uri = str;
        Pattern forFragment = Pattern.compile("#.+$");
        Matcher matcherForFragment = forFragment.matcher(str);
        if (matcherForFragment.find()) {
            this.fragment = str.substring(matcherForFragment.start() + FOR_CHAR_SHIFT);
            checkFragment();
            str = str.replace("#" + this.fragment, "");
        }

        Pattern forScheme = Pattern.compile("^.+?:");
        Matcher matcherForScheme = forScheme.matcher(str);
        if (matcherForScheme.find()) {
            this.scheme = str.substring(0, matcherForScheme.end());
            checkScheme();
            str = str.replace(this.scheme + ":", "");
        }

        this.schemeSpecificPart = str;
        checkSSP();

        Pattern forQuery = Pattern.compile("\\?.+");
        Matcher matcherForQuery = forQuery.matcher(str);
        if (matcherForQuery.find()) {
            this.query = str.substring(matcherForQuery.start() + FOR_CHAR_SHIFT);
            checkQuery();
            str = str.replace("?" + this.query, "");
        }

        Pattern forSlash = Pattern.compile("^/+");
        Matcher matcherForSlash = forSlash.matcher(str);
        if (matcherForSlash.find()) {
            str = str.substring(matcherForSlash.end());
        }

        Pattern forAuthority = Pattern.compile("^.+?/");
        Matcher matcherForAuthority = forAuthority.matcher(str);
        if (matcherForAuthority.find()) {
            this.authority = str.substring(0, matcherForAuthority.end());
            checkAuthority();
            str = str.replace(this.authority, "");
        }

        this.path = str;
        checkPath();

        str = this.authority;
        Pattern forPort = Pattern.compile(":[0-9]+$");
        Matcher matcherForPort = forPort.matcher(str);
        if (matcherForPort.find()) {
            this.port = Integer.parseInt(str.substring(matcherForPort.start() + 1));
            checkPort();
            str = str.replace(":" + this.port, "");
        }

        Pattern forUserInfo = Pattern.compile("^.+?@");
        Matcher matcherForUserInfo = forUserInfo.matcher(str);
        if (matcherForUserInfo.find()) {
            this.userInfo = str.substring(0, matcherForUserInfo.end());
            checkUserInfo();
            str = str.replace(this.userInfo + "@", "");
        }

        this.host = str;
        checkHost();
    }

    public String getScheme() {
        return scheme;
    }

    public String getSchemeSpecificPart() {
        return schemeSpecificPart;
    }

    public String getAuthority() {
        return authority;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public String getFragment() {
        return fragment;
    }

    public String getRegexScheme() throws URISyntaxException {
        Pattern forScheme = Pattern.compile("^.+?:");
        Matcher matcherForScheme = forScheme.matcher(this.uri);
        if (matcherForScheme.find()) {
            this.scheme = this.uri.substring(0, matcherForScheme.end());
            checkScheme();
        }
        return this.scheme;
    }

    public String getRegexSchemeSpecificPart() throws URISyntaxException {
        this.schemeSpecificPart = this.uri.replace("#" + getRegexFragment(), "");
        this.schemeSpecificPart = this.schemeSpecificPart.replace(getRegexScheme() + ":", "");
        checkSSP();
        return this.schemeSpecificPart;
    }

    public String getRegexAuthority() throws URISyntaxException {
        Pattern forSlash = Pattern.compile("^/+");
        Matcher matcherForSlash = forSlash.matcher(getRegexSchemeSpecificPart());
        if (matcherForSlash.find()) {
            this.authority = getRegexSchemeSpecificPart().substring(matcherForSlash.end());
        }
        Pattern forAuthority = Pattern.compile("^.+?/");
        Matcher matcherForAuthority = forAuthority.matcher(this.authority);
        if (matcherForAuthority.find()) {
            this.authority = this.authority.substring(0, matcherForAuthority.end());
            checkAuthority();
        }
        return this.authority;
    }

    public String getRegexUserInfo() throws URISyntaxException {
        Pattern forUserInfo = Pattern.compile("^.+?@");
        Matcher matcherForUserInfo = forUserInfo.matcher(getRegexAuthority());
        if (matcherForUserInfo.find()) {
            this.userInfo = getRegexAuthority().substring(0, matcherForUserInfo.end());
            checkUserInfo();
        }
        return this.userInfo;
    }

    public String getRegexHost() throws URISyntaxException {
        this.host = getRegexAuthority().replace(":" + getRegexPort(), "");
        this.host = this.host.replace(this.userInfo + "@", "");
        checkHost();
        return this.host;
    }

    public int getRegexPort() throws URISyntaxException {
        Pattern forPort = Pattern.compile(":[0-9]+$");
        Matcher matcherForPort = forPort.matcher(getRegexAuthority());
        if (matcherForPort.find()) {
            this.port = Integer.parseInt(getRegexAuthority().substring(matcherForPort.start() + 1));
            checkPort();
        }
        return this.port;
    }

    public String getRegexPath() throws URISyntaxException {
        this.path = getRegexSchemeSpecificPart().replace("?" + getRegexQuery(), "");
        this.path = this.path.replace(getRegexAuthority(), "");
        checkPath();
        return this.path;
    }

    public String getRegexQuery() throws URISyntaxException {
        Pattern forQuery = Pattern.compile("\\?.+");
        Matcher matcherForQuery = forQuery.matcher(getRegexSchemeSpecificPart());
        if (matcherForQuery.find()) {
            this.query = getRegexSchemeSpecificPart().substring(matcherForQuery.start() + FOR_CHAR_SHIFT);
            checkQuery();
        }
        return this.query;
    }

    public String getRegexFragment() {
        Pattern forFragment = Pattern.compile("#.+$");
        Matcher matcherForFragment = forFragment.matcher(this.uri);
        if (matcherForFragment.find()) {
            this.fragment = this.uri.substring(matcherForFragment.start() + FOR_CHAR_SHIFT);
            checkFragment();
        }
        return this.fragment;
    }
}
