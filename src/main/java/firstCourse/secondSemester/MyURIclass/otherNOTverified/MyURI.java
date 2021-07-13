package firstCourse.secondSemester.MyURIclass.otherNOTverified;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyURI {

    private String scheme;
    private String schemeSpecificPart;
    private String authority;
    private String userInfo;
    private String host;
    private int port = -1;
    private String path;
    private String query;
    private String fragment;

    public MyURI(String str) throws URISyntaxException {
        this.createNewURI(str);
    }

    public MyURI(String scheme, String ssp, String fragment) throws URISyntaxException {
        String result = "";
        if (scheme != null && !scheme.equals("")) result = result.concat(scheme + ":");
        if (ssp != null && !ssp.equals("")) result = result.concat(ssp);
        if (fragment != null && !fragment.equals("")) result = result.concat("#" + fragment);
        this.createNewURI(result);
    }

    public MyURI(String scheme, String userInfo, String host, int port,
                 String path, String query, String fragment) throws URISyntaxException {
        String result = "";
        if (scheme != null && !scheme.equals("")) result = result.concat(scheme + ":");
        if ((userInfo != null && !userInfo.equals("")) || (host != null && !host.equals("")) || port != -1)
            result = result.concat("//");
        if (userInfo != null && !userInfo.equals("")) result = result.concat(userInfo + "@");
        if (host != null && !host.equals("")) result = result.concat(host);
        if (port != -1) result = result.concat(":" + port);
        if (path != null && !path.equals("")) result = result.concat("/" + path);
        if (query != null && !query.equals("")) result = result.concat("?" + query);
        if (fragment != null && !fragment.equals("")) result = result.concat("#" + fragment);
        this.createNewURI(result);
        this.parseServerAuthority();
    }

    public MyURI(String scheme, String host, String path, String fragment) throws URISyntaxException {
        this(scheme, null, host, -1, path, null, fragment);
    }

    public MyURI(String scheme, String authority, String path, String query, String fragment) throws URISyntaxException {
        String result = "";
        if (scheme != null && !scheme.equals("")) result = result.concat(scheme + ":");
        if (authority != null && !authority.equals("")) result = result.concat("//" + authority);
        if (path != null && !path.equals("")) result = result.concat("/" + path);
        if (query != null && !query.equals("")) result = result.concat("?" + query);
        if (fragment != null && !fragment.equals("")) result = result.concat("#" + fragment);
        this.createNewURI(result);
        this.parseServerAuthority();
    }

    private void createNewURI(String str) {
        Pattern forFragment = Pattern.compile("#.+?$");
        Matcher matcherForFragment = forFragment.matcher(str);
        if (matcherForFragment.find()) {
            this.fragment = str.substring(matcherForFragment.start() + 1);
            str = str.replace("#" + this.fragment, "");
        }
        Pattern forScheme = Pattern.compile("^.+?:");
        Matcher matcherForScheme = forScheme.matcher(str);
        if (matcherForScheme.find()) {
            this.scheme = str.substring(0, matcherForScheme.end());
            str = str.replace(this.scheme + ":", "");
        }
        this.schemeSpecificPart = str;
        Pattern forQuery = Pattern.compile("\\?.+");
        Matcher matcherForQuery = forQuery.matcher(str);
        if (matcherForQuery.find()) {
            this.query = str.substring(matcherForQuery.start() + 1);
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
            str = str.replace(this.authority, "");
        }
        this.path = str;
        this.parseServerAuthority();
    }

    public int compareTo(MyURI that) {
        if (this.scheme.compareToIgnoreCase(that.scheme) != 0) return this.scheme.compareToIgnoreCase(that.scheme);
        if (this.isOpaque() && !that.isOpaque()) return 1;
        if (!this.isOpaque() && that.isOpaque()) return -1;
        if (this.schemeSpecificPart.compareToIgnoreCase(that.schemeSpecificPart) != 0)
            return this.schemeSpecificPart.compareToIgnoreCase(that.schemeSpecificPart);
        if (this.fragment.compareToIgnoreCase(that.fragment) != 0)
            return this.fragment.compareToIgnoreCase(that.fragment);
        if (this.authority.compareToIgnoreCase(that.authority) != 0)
            return this.authority.compareToIgnoreCase(that.authority);
        if (this.userInfo.compareToIgnoreCase(that.userInfo) != 0)
            return this.userInfo.compareToIgnoreCase(that.userInfo);
        if (this.host.compareToIgnoreCase(that.host) != 0)
            return this.host.compareToIgnoreCase(that.host);
        if (this.port != that.port)
            return this.port - that.port;
        if (this.path.compareToIgnoreCase(that.path) != 0)
            return this.path.compareToIgnoreCase(that.path);
        return this.query.compareToIgnoreCase(that.query);
    }

    public static MyURI create(String str) {
        try {
            return new MyURI(str);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("The given string violates RFC 2396");
        }
    }

    public boolean isAbsolute() {
        return (this.scheme != null && !this.scheme.equals(""));
    }

    public boolean isOpaque() {
        Pattern forSlash = Pattern.compile("^/+");
        Matcher matcherForSlash = forSlash.matcher(this.schemeSpecificPart);
        return (!matcherForSlash.find());
    }

    public MyURI normalize() {
        if (this.isOpaque()) return this;
        String str = this.toString();
        Pattern forDot = Pattern.compile("[^.]\\.[^.]");
        Matcher matcherForDot = forDot.matcher(str);
        while (matcherForDot.find()) {
            str = str.replace(str.substring(matcherForDot.start(), matcherForDot.start() + 1), "");
        }
        Pattern forDoubleDot = Pattern.compile("\\.{2}[^.]{2}");
        Matcher matcherForDoubleDot = forDoubleDot.matcher(str);
        while (matcherForDoubleDot.find()) {
            str = str.replace(str.substring(matcherForDot.start(), matcherForDot.start() + 1), "");
            matcherForDoubleDot = forDoubleDot.matcher(str);
        }
        try {
            return new MyURI(str);
        } catch (URISyntaxException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public MyURI parseServerAuthority() {
        if (userInfo != null && !userInfo.equals("") &&
                host != null && !host.equals("") &&
                port != -1) return this;
        String str = this.authority;
        Pattern forPort = Pattern.compile(":[0-9]+$");
        Matcher matcherForPort = forPort.matcher(str);
        if (matcherForPort.find()) {
            this.port = Integer.parseInt(str.substring(matcherForPort.start() + 1));
            str = str.replace(":" + this.port, "");
        }
        Pattern forUserInfo = Pattern.compile("^.+?@");
        Matcher matcherForUserInfo = forUserInfo.matcher(str);
        if (matcherForUserInfo.find()) {
            this.userInfo = str.substring(0, matcherForUserInfo.end());
            str = str.replace(this.userInfo + "@", "");
        }
        this.host = str;
        return this;
    }

    public MyURI relativize(MyURI uri) {
        if (uri == null) throw new NullPointerException("null");
        if (this.isOpaque() || uri.isOpaque() || !this.scheme.equals(uri.scheme)
                || !this.authority.equals(uri.authority) || uri.path.indexOf(this.path) != 0) return uri;
        try {
            return new MyURI(uri.scheme, uri.userInfo, uri.host, uri.port, uri.path.replace(this.path, ""),
                    uri.query, uri.fragment);
        } catch (URISyntaxException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public MyURI resolve(String str) {
        if (str == null) throw new NullPointerException("null");
        return this.resolve(MyURI.create(str));
    }

    public MyURI resolve(MyURI uri) {
        this.parseServerAuthority();
        uri.parseServerAuthority();
        if (this.scheme == null || this.scheme.equals("")) this.scheme = uri.scheme;
        if (this.schemeSpecificPart == null || this.schemeSpecificPart.equals(""))
            this.schemeSpecificPart = uri.schemeSpecificPart;
        if (this.authority == null || this.authority.equals("")) this.authority = uri.authority;
        if (this.userInfo == null || this.userInfo.equals("")) this.userInfo = uri.userInfo;
        if (this.host == null || this.host.equals("")) this.host = uri.host;
        if (this.port == -1) this.port = uri.port;
        if (this.path == null || this.path.equals("")) this.path = uri.path;
        if (this.query == null || this.query.equals("")) this.query = uri.query;
        if (this.fragment == null || this.fragment.equals("")) this.fragment = uri.fragment;
        return this;
    }

    public String toASCIIString() {
        return this.toString();
    }

    public URL toURL() {
        if (!this.isAbsolute()) throw new IllegalArgumentException("this URI isn't absolute");
        try {
            return new URL(this.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.scheme != null && !this.scheme.equals("")) result = result.concat(this.scheme + ":");
        result = result.concat(this.schemeSpecificPart);
        if (this.fragment != null && !this.fragment.equals("")) result = result.concat("#" + this.fragment);
        return result;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (this == ob) return true;
        if (!(ob instanceof MyURI)) return false;
        MyURI that = (MyURI) ob;
        return this.scheme.equals(that.scheme) && this.schemeSpecificPart.equals(that.schemeSpecificPart) &&
                this.authority.equals(that.authority) && this.userInfo.equals(that.userInfo) &&
                this.host.equals(that.host) && this.path.equals(that.path) &&
                this.query.equals(that.query) && this.fragment.equals(that.fragment) && this.port == that.port;
    }

    @Override
    public int hashCode() {
        this.parseServerAuthority();
        return 31 * Objects.hash(getScheme(), getSchemeSpecificPart(), getAuthority(), getUserInfo(), getHost(),
                getPort(), getPath(), getQuery(), getFragment());
    }

    public String getAuthority() {
        return getRawAuthority();
    }

    public String getFragment() {
        return getRawFragment();
    }

    public String getHost() {
        this.parseServerAuthority();
        return host;
    }

    public String getPath() {
        return getRawPath();
    }

    public int getPort() {
        this.parseServerAuthority();
        return port;
    }

    public String getQuery() {
        return getRawQuery();
    }

    public String getRawAuthority() {
        return authority;
    }

    public String getRawFragment() {
        return fragment;
    }

    public String getRawPath() {
        return path;
    }

    public String getRawQuery() {
        return query;
    }

    public String getRawSchemeSpecificPart() {
        return schemeSpecificPart;
    }

    public String getRawUserInfo() {
        this.parseServerAuthority();
        return userInfo;
    }

    public String getScheme() {
        return scheme;
    }

    public String getSchemeSpecificPart() {
        return getRawSchemeSpecificPart();
    }

    public String getUserInfo() {
        return getRawUserInfo();
    }

}
