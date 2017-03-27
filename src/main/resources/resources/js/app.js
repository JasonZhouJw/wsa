var Application={

    ActiveTemplate:{
        'options':'<option value="{{value}}"{{#selected}} selected="selected"{{/selected}}>{{label}}</option>',
        'dropBox':'{{#options}}{{>option}}{{/options}}'
    },

    Init:function(data){
        this.Active(data.activeList, data.searchParam);
        console.log(data);
//
    },

    Active: function (statusList, searchParam){
        if(searchParam){
            statusList.forEach(function (status){
                        if(status.value== searchParam.active){
                            status.selected=true;
                        }
                    });
        }
        $('#active').append(Mustache.render(this.ActiveTemplate.dropBox, statusList, this.ActiveTemplate));
    }


}