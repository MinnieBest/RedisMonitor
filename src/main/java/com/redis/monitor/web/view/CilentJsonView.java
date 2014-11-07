package com.redis.monitor.web.view;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.redis.monitor.json.FastJson;



public class CilentJsonView extends AbstractView {
	
	public static final String DEFAULT_CONTENT_TYPE = "application/json";
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	private String encoding;
	
	public CilentJsonView() {
		setContentType(DEFAULT_CONTENT_TYPE);
		setEncoding(DEFAULT_ENCODING);
	}

	@Override
	protected void prepareResponse(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType(getContentType());
		response.setCharacterEncoding(getEncoding());
	}



	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OutputStream output = null;
		try {
			output = response.getOutputStream();
			IOUtils.write(model.get("data") == null ? FastJson.toJson(model) : FastJson.toJson(model.get("data")), output,"UTF-8");
		} finally {
			if (output != null)
				output.close();
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
