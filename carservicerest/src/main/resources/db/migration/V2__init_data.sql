insert into cars(id, car_code, make, model, year) values (1, 'j7cAeeW8ny', 'Ford', 'F150 SuperCrew Cab', 2016);
insert into cars(id, car_code, make, model, year) values (2, 'cptB1C1NSL', 'Chevrolet', 'Malibu', 2020);
insert into cars(id, car_code, make, model, year) values (3, '4q7L9FAU2S', 'Chrysler', 'Pacifica', 2019);

insert into categories(id, category_name) values (1,'Sedan');
insert into categories(id, category_name) values (2,'SUV');
insert into categories(id, category_name) values (3,'Pickup');
insert into categories(id, category_name) values (4,'Minivan');

insert into car_category(car_id, category_id) values (1, 1); 
insert into car_category(car_id, category_id) values (3, 4);
insert into car_category(car_id, category_id) values (2, 1);

insert into catters(id, catter_name, car_id) values (1, 'May', 2);
insert into catters(id, catter_name, car_id) values (2, 'May1', 1);
insert into catters(id, catter_name, car_id) values (3, 'May2', 3);