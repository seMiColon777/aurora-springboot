package com.aurora.util;

import com.aurora.constant.CommonConstant;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class IpUtil {

    public static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    private static Searcher searcher;

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("getIpAddress exception:", e);
                }
                assert inet != null;
                ipAddress = inet.getHostAddress();
            }
        }
        return StringUtils.substringBefore(ipAddress, ",");
    }

    @PostConstruct
    private void initIp2regionResource() throws Exception {
        InputStream inputStream = new ClassPathResource("/ip/ip2region.db").getInputStream();
        byte[] cBuff = FileCopyUtils.copyToByteArray(inputStream);
        searcher = Searcher.newWithBuffer(cBuff);
    }

    public static String getIpSource(String ipAddress) {
        String addr;
        try {
            addr = searcher.search(ipAddress);
        } catch (Exception e) {
            logger.error("获取地址异常", e);
            addr = "";
        }
        return addr;
    }

    public static String getIpProvince(String ipSource) {
        if (StringUtils.isBlank(ipSource)) {
            return CommonConstant.UNKNOWN;
        }
        String[] strings = ipSource.split("\\|");
        if (strings.length > 1 && strings[1].endsWith("省")) {
            return StringUtils.substringBefore(strings[1], "省");
        }
        return strings[0];
    }

    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

}
