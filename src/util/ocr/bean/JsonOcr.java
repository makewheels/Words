/**
  * Copyright 2018 bejson.com 
  */
package util.ocr.bean;
import java.util.List;

/**
 * Auto-generated: 2018-02-09 18:20:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonOcr {

    private String orientation;
    private String errorCode;
    private List<Lines> lines;
    public void setOrientation(String orientation) {
         this.orientation = orientation;
     }
     public String getOrientation() {
         return orientation;
     }

    public void setErrorCode(String errorCode) {
         this.errorCode = errorCode;
     }
     public String getErrorCode() {
         return errorCode;
     }

    public void setLines(List<Lines> lines) {
         this.lines = lines;
     }
     public List<Lines> getLines() {
         return lines;
     }

}