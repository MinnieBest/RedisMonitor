<%@page import="com.redis.monitor.RedisJedisPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String uuid = RedisJedisPool.getAllRedisServer().get(0).getUuid();
response.sendRedirect("/index.htm?uuid=" + uuid);
%>
