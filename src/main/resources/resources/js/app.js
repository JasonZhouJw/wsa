var Application={

    DropDownTemplate:{
        'option':'<option value="{{value}}"{{#selected}} selected="selected"{{/selected}}>{{label}}</option>',
        'dropBox':'{{#options}}{{>option}}{{/options}}'
    },

    Init:function(data){
        this.DropDown("active",data.activeList, data.searchParam, function(option, selected){
            return option.value==String(selected.active);
        });
    },

    DropDown: function (id, options, selected, condition){
        if(selected){
            options.forEach(function (option){
                if(condition && condition(option, selected)){
                    option.selected=true;
                }
            });
        }
        $('#'+id).append(Mustache.render(this.DropDownTemplate.dropBox, {'options':options}, this.DropDownTemplate));
    }


}