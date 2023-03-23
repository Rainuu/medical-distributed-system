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