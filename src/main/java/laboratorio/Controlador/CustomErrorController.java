package laboratorio.Controlador;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        Integer statusCode = (Integer) attributes.get("status");

        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    return "error404";
                case 500:
                    return "error500";
                case 403:
                    return "error403";
                default:
                    return "error";
            }
        }

        return "error";
    }
    public String getErrorPath() {
        return "/error";
    }
}
