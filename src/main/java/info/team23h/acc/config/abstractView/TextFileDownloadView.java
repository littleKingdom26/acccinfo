package info.team23h.acc.config.abstractView;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

@Slf4j
@Component("textFileDownloadView")
public class TextFileDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String text = (String) model.get("text");
		String fileName = (String) model.get("fileName");
		setContentType("text/plain; charset=utf-8");
		response.setContentType(getContentType());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		try(InputStream in = new ByteArrayInputStream(text.getBytes()); OutputStream out = response.getOutputStream()) {
			response.setContentLength(in.available());
			IOUtils.copy(in, out);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
