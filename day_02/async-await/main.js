let money = 35

const buyIphone = () => {
    return new Promise(function(resolve, reject) {
        if(money >= 33) {
            resolve("Mua iphone thành công");
        } else {
            reject("Không đủ tiền mua iphone, cày tiếp đi")
        }
    })
}

const buyAirpod = () => {
    return new Promise(function(resolve, reject) {
        if(money >= 33 + 5) {
            resolve("Mua thêm Airpod");
        } else {
            reject("Không đủ tiền mua Airpod")
        }
    })
}

// async function buy() {

// }

const buy = async () => {
    try {
        let res = await buyIphone()
        console.log(res);

        let res1 = await buyAirpod()
        console.log(res1);
    } catch (error) {
        console.log(error);
    }

    return "Về nhà"
}

// console.log(buy());
buy()
    .then(res => console.log(res))
    .catch(error => console.log(error))
