// const OSS = require('ali-oss')
//
// export function client(data){
//     //后端提供数据
//     return new OSS({
//         region: data.endpoint,
//         accessKeyId: data.accessKeyId,
//         accessKeySecret: data.accessKeySecret,
//         bucket: data.bucketName,
//         endpoint: data.endpoint,
//         secure: true
//     })
// }
import OSS from 'ali-oss';

export default (conf) => {
    return new OSS(conf);
}