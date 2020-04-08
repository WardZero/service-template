package com.wfdss.nextgen.servicetemplate.integrationservice;

import com.wfdss.nextgen.servicetemplate.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SampleIntegrationService {
    private RestTemplate restTemplate;
    private String restApiExampleGetEmployeeByIdUrl = "http://dummy.restapiexample" +
            ".com/api/v1/employee/1";

    public SampleIntegrationService() {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }

    /**
     * Method to retrieve a single Employee from http://dummy.restapiexample.com
     *
     * @return Employee Object
     */
    public Employee getEmployee() {
        Employee employee = restTemplate.getForObject(restApiExampleGetEmployeeByIdUrl,
                Employee.class);
        return employee;
    }

    /**
     * Method for appending parameters to a GET request, to maintain appropriate syntax.
     *
     * @param uri The Base URI
     * @param appendQuery The query parameter to be appended. E.x. id=3
     * @return URI object with query appended.
     * @throws URISyntaxException
     */
    public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri;
    }

}
