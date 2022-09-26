INSERT INTO area(id, name, base_charge, has_delivery)
VALUES (1, 'Bulawayo', 4.56, false),
       (2, 'Phnom Penh', 15.38, true),
       (3, 'Palembang', null, false),
       (4, 'Amsterdam', 238.8, true),
       (5, 'Gaborone', 42.6, true);
ALTER SEQUENCE areas_id_seq RESTART WITH 6;

INSERT INTO extra_charge(id, min_weight, max_weight, charge, area_id)
VALUES (1, 6.00, 15.50, 3.47, 1),
       (2, 1.25, 5.75, 1.53, 1),
       (3, 14, 17, 8.35, 5);
ALTER SEQUENCE extra_charges_id_seq RESTART WITH 4;
