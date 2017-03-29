var TestCaseIndex={

    Init:function(data){
        var application=Object.create(Application).Init(data);
        application.DropDown("active", data.activeList, data.searchParam, function(option, selected){
            return option.value==String(selected.active);
         });
    }

}