package com.tmsps.traceback.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tmsps.traceback.model.t_fk_admin;
import com.tmsps.traceback.util.json.JsonTools;
import com.tmsps.traceback.web.SessionTools;

/**
 * 分页拦截器，定义拦截路径 /cp
 * 
 * @author uninf
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		t_fk_admin login_user = SessionTools.getCurrentLoginCpAdmin();
		if (login_user == null) {
			if (handler.getClass() == HandlerMethod.class) {
				if (handler.getClass() == HandlerMethod.class) {
					HandlerMethod hm = (HandlerMethod) handler;
					ResponseBody methodAnnotation = hm.getMethodAnnotation(ResponseBody.class);

					if (methodAnnotation != null && methodAnnotation.annotationType() == ResponseBody.class) {
						response.getWriter().print(JsonTools.jsonStrToJsonObject("{'loginVar':'false'}"));
					} else {
						String end = "<script>window.top.location.href='@path';</script>";
						response.getWriter().print(end.replace("@path", request.getContextPath()));
					}
				}

				return false;

			}

			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
