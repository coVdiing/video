import {alertWarn} from './toast.js';

function require(value, text) {
    if (Tool.isEmpty(value)) {
        alertWarn(text + "不能为空");
        return false;
    } else {
        return true
    }
}

function length(value, text, min, max) {
    if (Tool.isEmpty(value)) {
        return true;
    }
    if (!Tool.isLength(value, min, max)) {
        alertWarn(text + "长度" + min + "~" + max + "位");
        return false;
    } else {
        return true
    }
}

export {require, length}