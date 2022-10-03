const CURRENCY_REGEX = /^-?\d+(\.\d{1,2})?$/;
const WEIGHT_REGEX = /^\d+(\.\d+)?$/;

export const checkCostIsValid = (value) => {
    return CURRENCY_REGEX.test(value);
}

export const checkMarkupWeight = (min, max) => {
    return WEIGHT_REGEX.test(min) && WEIGHT_REGEX.test(max) && (min < max);
}

export const checkArea = (area) => {
    const array = [];
    array.push(checkCostIsValid(area.baseCharge));
    Array.from(area.extraCharges).map((charge, _i) => {
        array.push(checkCostIsValid(charge.charge));
        array.push(checkMarkupWeight(charge.minWeight, charge.maxWeight));
    })
    return array.includes(false);
}