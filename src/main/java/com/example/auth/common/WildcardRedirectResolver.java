package com.example.auth.common;

import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class WildcardRedirectResolver extends DefaultRedirectResolver {

    private static final String WILDCARD_PATTERN = "**";

    private boolean matchPorts = true;

    @Override
    public void setMatchPorts(boolean matchPorts) {
        super.setMatchPorts(matchPorts);

        this.matchPorts = matchPorts;
    }

    @Override
    protected boolean redirectMatches(String requestedRedirect, String redirectUri) {
        UriComponents requestedRedirectUri = UriComponentsBuilder.fromUriString(requestedRedirect).build();
        String requestedRedirectUriScheme = (requestedRedirectUri.getScheme() != null ? requestedRedirectUri.getScheme() : "");
        String requestedRedirectUriHost = (requestedRedirectUri.getHost() != null ? requestedRedirectUri.getHost() : "");
        String requestedRedirectUriPath = (requestedRedirectUri.getPath() != null ? requestedRedirectUri.getPath() : "");

        UriComponents registeredRedirectUri = UriComponentsBuilder.fromUriString(redirectUri).build();
        String registeredRedirectUriScheme = (registeredRedirectUri.getScheme() != null ? registeredRedirectUri.getScheme() : "");
        String registeredRedirectUriHost = (registeredRedirectUri.getHost() != null ? registeredRedirectUri.getHost() : "");
        String registeredRedirectUriPath = (registeredRedirectUri.getPath() != null ? registeredRedirectUri.getPath() : "");

        boolean portsMatch = this.matchPorts ? (registeredRedirectUri.getPort() == requestedRedirectUri.getPort()) : true;

        return registeredRedirectUriScheme.equals(requestedRedirectUriScheme) &&
                hostMatches(registeredRedirectUriHost, requestedRedirectUriHost) &&
                portsMatch &&
                // Asterisk path matching or Exact path matching
                this.wildcardMatche(registeredRedirectUriPath, StringUtils.cleanPath(requestedRedirectUriPath));

    }

    private boolean wildcardMatche(final String registeredRedirectUriPath, final String requestedRedirectUriPath) {

        String cleanedPath = StringUtils.cleanPath(requestedRedirectUriPath);

        boolean result = false;
        if (registeredRedirectUriPath.endsWith(WILDCARD_PATTERN)) {
            int idx = registeredRedirectUriPath.indexOf(WILDCARD_PATTERN);

            if (idx <= cleanedPath.length()) {
                result = registeredRedirectUriPath.startsWith(cleanedPath.substring(0, idx));
            }
        } else {
            result= registeredRedirectUriPath.equals(cleanedPath);
        }

        return result;
    }
}
