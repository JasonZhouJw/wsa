var VerifyResultIndex={

    Init:function(data){
            this.application=Object.create(Application).Init(data);
            this.application.DropDown("result",data.resultType,data.searchParam, function(option, searchParam){
                return option.value==searchParam.resultType.value;
            })
        }

}