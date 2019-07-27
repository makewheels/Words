/**
  * Copyright 2018 bejson.com 
  */
package youdao.bean;
import java.util.List;

/**
 * Auto-generated: 2018-02-08 22:23:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonResult {

    private List<Web> web;
    private String query;
    private List<String> translation;
    private String errorCode;
    private Dict dict;
    private Webdict webdict;
    private Basic basic;
    private String l;
    public void setWeb(List<Web> web) {
         this.web = web;
     }
     public List<Web> getWeb() {
         return web;
     }

    public void setQuery(String query) {
         this.query = query;
     }
     public String getQuery() {
         return query;
     }

    public void setTranslation(List<String> translation) {
         this.translation = translation;
     }
     public List<String> getTranslation() {
         return translation;
     }

    public void setErrorCode(String errorCode) {
         this.errorCode = errorCode;
     }
     public String getErrorCode() {
         return errorCode;
     }

    public void setDict(Dict dict) {
         this.dict = dict;
     }
     public Dict getDict() {
         return dict;
     }

    public void setWebdict(Webdict webdict) {
         this.webdict = webdict;
     }
     public Webdict getWebdict() {
         return webdict;
     }

    public void setBasic(Basic basic) {
         this.basic = basic;
     }
     public Basic getBasic() {
         return basic;
     }

    public void setL(String l) {
         this.l = l;
     }
     public String getL() {
         return l;
     }

}