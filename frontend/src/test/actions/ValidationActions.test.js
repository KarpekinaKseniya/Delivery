import {checkArea, checkCostIsValid, checkMarkupWeight} from "../../actions/ValidationActions";

describe('Check currency is  valid', () => {
    it.each([
        [12.23, true],
        [-34.23, true],
        [1212.2323, false],
        ['string', false],
        [1323, true],
        ['232,23', false],
        ['1,000,000.34', false]
    ])('Check %p expecting %p', (value, result) => {
        expect(checkCostIsValid(value)).toEqual(result);
    });
})

describe('Check markup weights is valid', () => {
    it.each([
        [10, 25, true],
        [11.1, 23.213, true],
        [-1, 12.12, false],
        [1, '4,23', false],
        [35, 21, false]
    ])('Check min = %p, max = %p expecting %p', (min, max, result) => {
        expect(checkMarkupWeight(min, max)).toEqual(result);
    });
})

describe('Check area is  valid', () => {
    it.each([
        [{
            baseCharge: 4.56,
            extraCharges: [
                {
                    minWeight: 6.0,
                    maxWeight: 15.5,
                    charge: 3.47
                },
                {
                    minWeight: 1.25,
                    maxWeight: 5.75,
                    charge: 1.53
                }
            ]
        }, false],
        [{
            baseCharge: 1.50,
            extraCharges: [
                {
                    minWeight: 16.0,
                    maxWeight: 15.5,
                    charge: 0.50
                }
            ]
        }, true]
    ])('Check %p expecting %p', (value, result) => {
        expect(checkArea(value)).toEqual(result);
    });
})

