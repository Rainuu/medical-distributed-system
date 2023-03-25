export  function formatDict(dictList,column,dictType){
    let r='';
    for (let i = 0; i < dictList.length; i++) {
        if (dictList[i].dictType===dictType){
            if (dictList[i].dictValue === column) {
                r= dictList[i].dictLabel;
                break;
            }
        }
    }
    return r;
}

//格式化数据字典
export function formatDict3(datas,value){
    for(let i=0;i<datas.length;i++){
        if(datas[i].dictValue==value){
            return datas[i].dictLabel;
        }
    }
    return "";
}