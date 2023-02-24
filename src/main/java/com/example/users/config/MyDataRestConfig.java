/*
 * package com.example.users.config;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.data.rest.core.config.RepositoryRestConfiguration; import
 * org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer; import
 * org.springframework.http.HttpMethod;
 * 
 * import com.example.users.entity.Comment; import
 * com.example.users.entity.Post;
 * 
 * 
 * 
 * @Configuration public class MyDataRestConfig implements
 * RepositoryRestConfigurer {
 * 
 * public void configureRepositoryRestConfiguration(RepositoryRestConfiguration
 * config) {
 * 
 * HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE,
 * HttpMethod.PATCH};
 * 
 * // disable HTTP methods for Product: PUT, POST, DELETE and PATCH
 * config.getExposureConfiguration() .forDomainType(Post.class)
 * .withItemExposure((metdata, httpMethods) ->
 * httpMethods.disable(theUnsupportedActions)) .withCollectionExposure((metdata,
 * httpMethods) -> httpMethods.disable(theUnsupportedActions));
 * 
 * // disable HTTP methods for ProductCategory: PUT, POST, DELETE and PATCH
 * config.getExposureConfiguration() .forDomainType(Comment.class)
 * .withItemExposure((metdata, httpMethods) ->
 * httpMethods.disable(theUnsupportedActions)) .withCollectionExposure((metdata,
 * httpMethods) -> httpMethods.disable(theUnsupportedActions));
 * 
 * } }
 */