export function formatDict(dates,value){
    /*this.$axios.get('/system/api/dict/data/findByType?type='+dates).then(res=>{
       let dictlist=res.data.t*/
        for (let i=0;i<dates.length;i++){
            if (dates[i].dictValue==value){
                return dates[i].dictLabel;
            }
        }
    return "";
}