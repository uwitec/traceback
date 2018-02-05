Ext.apply(Ext.form.VTypes, {  
    daterange : function(val, field) {  
        var date = field.parseDate(val);  
        if (!date) {  
            return;  
        }  
        if (field.startDateField  
                && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax  
                        .getTime()))) {  
            var start = Ext.getCmp(field.startDateField);  
            start.setMaxValue(date);  
            start.validate();  
            this.dateRangeMax = date;  
        } else if (field.endDateField  
                && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin  
                        .getTime()))) {  
            var end = Ext.getCmp(field.endDateField);  
            end.setMinValue(date);  
            //end.validate();  
            this.dateRangeMin = date;  
        }  
        /* 
         * Always return true since we're only using this vtype to set 
         * the min/max allowed values (these are tested for after the 
         * vtype test) 
         */  
        return true;  
    }  
});  