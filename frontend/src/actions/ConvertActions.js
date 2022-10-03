import {checkCostIsValid} from "./ValidationActions";

export const countTotalCharge = (base, extra) => {
    if (checkCostIsValid(base) && checkCostIsValid(extra)) {
        const result = parseFloat(base) + extra;
        return result.toFixed(2);
    }
    return NaN;
}