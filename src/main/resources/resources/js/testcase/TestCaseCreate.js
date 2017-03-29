var TestCaseCreate={

    application:null,

    Init:function(data){
        this.application=Object.create(Application).Init(data);
        this.ServicesInfoDropDown(data.servicesInfoList, data.testCase);
    },

    ServicesInfoDropDown: function (optionData, selected){
        var options=[];
        optionData.forEach(function (servicesInfo){
            var option={
                value:servicesInfo.id,
                label:servicesInfo.wsdl
            };
            if(selected){
                servicesInfo.methodInfoList.forEach(function (methodInfo){
                    if(methodInfo.id==selected.methodId){
                        option.selected=true;
                    }
                });
            }
            options.push(option);
        });
        $('#servicesInfo').append(Mustache.render(this.application.DropDownTemplate.dropBox, {'options':options}, this.application.DropDownTemplate));

         $("#servicesInfo").change(function(){
            var selectedValue=$(this).val();
            this.application.data.servicesInfoList.forEach(function(servicesInfo){
                if(servicesInfo.id==selectedValue){
                    this.MethodInfoDropDown(servicesInfo.methodInfoList,this.application.data.testCase);
                    return;
                }
            })
         });
    },

    MethodInfoDropDown:function(optionData, selected){
        var options=[];
                optionData.forEach(function (methodInfo){
                    var option={
                        value:methodInfo.id,
                        label:methodInfo.method
                    };
                    if(selected && methodInfo.id==selected.methodId){
                        option.selected=true;
                    }
                    options.push(option);
                });
                $('#methodInfo').append(Mustache.render(this.application.DropDownTemplate.dropBox, {'options':options}, this.application.DropDownTemplate));
    }

}