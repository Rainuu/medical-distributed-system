export function formatDict(dictList, column, dictType) {
    let r = '';  //初始化返回值
    for (let i = 0; i < dictList.length; i++) {  //循环前端的列表字段
        if (dictList[i].dictType === dictType) {  //根据字典字段类型对前端字段处理转类型
            if (dictList[i].dictValue === column) {  //根据字典字段值比对前端
                r = dictList[i].dictLabel;  //赋值并返回
                break;
            }
        }
    }
    return r;
}

//格式化数据字典2   刘鸿飞写的
export function formatDict2(datas, value) {
    for (let i = 0; i < datas.length; i++) {
        if (datas[i].dictValue == value) {
            return datas[i].dictLabel;
        }
    }
    return "";
}

// 可以重置任何页面的表单
export function resetForm(refName) {
    if (this.$refs[refName]) {
        this.$refs[refName].resetFields()
    }
}

