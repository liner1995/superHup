/**
 * 将查询后的列表数据集合根据替换规则替换为正确的字符
 * @param {查询结果集} result 
 * @param {替换规则} replace 
 */
export function initReplaceListData(result, replace) {
  // 数据格式化
  if (result) {
    if (replace) {
      for (let index in replace) {
        let r = replace[index]
        let prop = r.name
        let doReplace = r.replace
        for (let j in result) {
          let valueForResult = result[j][prop]
          for (let key in doReplace) {
            let value = doReplace[key]
            if (valueForResult == key) {
              result[j][prop] = value
            }
          }
        }
      }
    }
  }
  return result
}

/**
 * 更新数据时根据替换规则将数据替换为原本的结构
 * @param {更新数据} temp 
 * @param {替换规则} replace 
 */
export function initReplaceUpdateData(temp, replace, userid) {
  if (replace && temp) {
    for (let index in replace) {
      let repName = replace[index].name
      let repCont = replace[index].replace
      let tempVal = temp[repName]
      for (let key in repCont) {
        let repVal = repCont[key]
        if (repVal === tempVal) {
          temp[repName] = key
        }
      }
    }
  }

  if (temp) {
    temp.creator = null
    temp.modifier = userid
  }
}

/**
 * 处理后台逻辑后将数据添加到页面中
 * @param {新增数据} temp 
 * @param {替换规则} replace 
 */
export function initReplaceAddData(temp, replace, username) {
  if (replace && temp) {
    for (let index in replace) {
      let repName = replace[index].name
      let repCont = replace[index].replace
      let tempVal = temp[repName]
      for (let key in repCont) {
        let repVal = repCont[key]
        if (tempVal === key) {
          temp[repName] = repVal
        }
      }
    }
  }

  if (temp && !temp.creatorShow) {
    temp.creatorShow = username
  }
}

/**
 * 将数据库中多选空间存储的值转换为控件需要的值
 * @param { 多选空间存储值 } manySelected 
 */
export function initElSelectManyShowData(manySelected) {
  let selectedArr = []
  if (manySelected && typeof(manySelected) === 'string') {
    let valSplit = manySelected.split(',')
    if (valSplit) {
      for (let index in valSplit) {
        selectedArr.push(Number.parseInt(valSplit[index]))
      }
      return selectedArr
    }
  }
  return selectedArr
}

/**
 * 将多选择器控件的值转换为后台中存储的数据格式
 * @param { 控件中带出来的值 } manySelected 
 */
export function initElSelectManySaveData(manySelected) {
  let saveVal = ''
  if (manySelected) {
    for (let index in manySelected) {
      let valOne = manySelected[index]
      if (valOne) {
        saveVal = saveVal + valOne + ','
      }
    }
    saveVal = saveVal.substr(0, saveVal.length - 1)
  }
  return saveVal
}

/**
 * 格式化日期为字符串
 * @param {日期参数} time 
 * @param {格式参数} cFormat 
 */
export function parseTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (('' + time).length === 10) time = parseInt(time) * 1000
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    // Note: getDay() returns 0 on Sunday
    if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value ] }
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return time_str
}

/**
 * 生成随机数
 * @param {盐值} number 
 */
export function randomNum(number) {
  let today = new Date();
  let seed = today.getTime();
  return rand(number, seed)
}
function rand(number, seed){
  return Math.ceil(rnd(seed) * number);
}
function rnd(seed){
  seed = ( seed * 9301 + 49297 ) % 233280;
  return seed / ( 233280.0 );
}