import {countTotalCharge} from "../../actions/ConvertActions";

describe('Count Total Charge', () => {
    it.each([
        [4, 5, 9.00.toFixed(2)],
        [4.50, 3.50, 8.00.toFixed(2)],
        ['5.00', -1.50, 3.50.toFixed(2)],
        [1.214, 2.00, NaN],
        [2.32, '223.132', NaN],
        [21.121, 2132.232, NaN]
    ])('%p + %p = %p', (base, extra, result) => {
        expect(countTotalCharge(base, extra)).toEqual(result);
    });
})