package ch.presentium.backend.api.common;

import java.time.LocalDateTime;
import org.springframework.core.MethodParameter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class DateRangeResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return DateRange.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {
        String startStr = webRequest.getParameter("start");
        String endStr = webRequest.getParameter("end");
        Pair<LocalDateTime, LocalDateTime> dates = StringTimeParser.getStartEndDates(startStr, endStr);
        return new DateRange(dates.getFirst(), dates.getSecond());
    }
}
