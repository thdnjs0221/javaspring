/**
 * 
 */
$(function(){ // $(document).on("load|ready", function(){});
   let selectValue = $(calForm.operator).data("initValue");
   $(calForm.operator).val(selectValue);
   let successes = {
      // name:value
      json : function(resp){
         $(resultArea).html(resp.calVO.expression);
      },
      html : function(resp){
         $(resultArea).html(resp);
      }
   };
   $(calForm).on("submit", function(event){
      event.preventDefault();
      let url = this.action;
      let method = this.method;
      let settings = {
         url : url,
         method : method,
         error : function(jqXhr, status, error) {
            console.log("jqXhr : ", jqXhr);
            console.log("status : ", status);
            console.log("error : ", error);
         }
      };
      
      let contentType = $("[name=contentType]:checked").data("contentType");
      if(contentType && contentType.indexOf("json")>=0){
         let data = $(this).serializeJSON(); // js object
         let json = JSON.stringify(data);
         settings.contentType=contentType;
         settings.data=json;
      }else{
         let data = $(this).serialize(); // query string
         settings.data = data;
      }
      let dataType = $("[name=accept]:checked").val() ?? "json";
      settings["dataType"] = dataType;      //널병합연산자
      settings.success = successes[dataType];
      
      $.ajax(settings);
      return false;
   });
});


