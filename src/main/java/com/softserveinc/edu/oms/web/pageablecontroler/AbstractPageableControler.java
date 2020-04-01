/**
 * 
 */
package com.softserveinc.edu.oms.web.pageablecontroler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import com.softserveinc.edu.oms.web.ControlerErrorException;
import com.softserveinc.edu.oms.web.ControlerRedirectErrorException;
import com.softserveinc.edu.oms.web.pageablecontroler.util.PageJspType;
import com.softserveinc.edu.oms.web.pageablecontroler.util.PageModel;

/**
 * @author Vitalik
 * 
 */
public abstract class AbstractPageableControler {

	/**
	 * This method validate required parameters, create PageModel object and
	 * call "setPageData" function
	 * 
	 * @param request - request object
	 * @param modelMap - view model map
	 * @param jspName - JSP name
	 * @return JSP name or error page JSP name
	 */
	protected final String setModelMapDataForJsp(
			final HttpServletRequest request, final ModelMap modelMap,
			final String jspName, final PageJspType pageJspType) {
		try {
			if (isValidRequiredParameters(request)) {
				PageModel pageModel = new PageModel(
						getNumberOfAllElementsForPageModel(request), request);
				switch (pageJspType) {
				case JspWithAjax:
					setGeneralPageData(request, modelMap, pageModel);
					break;
				case JspWithoutAjax:
					setGeneralPageData(request, modelMap, pageModel);
					setCurrentPageData(request, modelMap, pageModel);
					break;
				case JspForCurrentPage:
					setCurrentPageData(request, modelMap, pageModel);
					break;
				}
				return jspName;
			}
		} catch (ControlerRedirectErrorException e) {
			return "redirect:errorpage?errorMessage=" + e.toString()
					+ "&redirectUrl" + e.getRedirectUrl();
		} catch (ControlerErrorException e) {
			return "redirect:errorpage?errorMessage=" + e.toString();
		} catch (Exception e) {
			return "redirect:errorpage?errorMessage=" + e.toString();
		}
		return "redirect:errorpage?errorMessage=uncatched error";
	}
	
	// Abstract methods

	protected abstract void setGeneralPageData(
			final HttpServletRequest request, final ModelMap modelMap,
			final PageModel pageModel);

	protected abstract void setCurrentPageData(
			final HttpServletRequest request, final ModelMap modelMap,
			final PageModel pageModel);

	protected abstract int getNumberOfAllElementsForPageModel(
			final HttpServletRequest request);

	protected abstract Boolean isValidRequiredParameters(
			final HttpServletRequest request) throws ControlerErrorException;
}
