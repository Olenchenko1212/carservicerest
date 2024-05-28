insert into carservicerest.cars(id, car_code, make, model, year)
values 	(1, 'j7cAeeW8ny', 'Ford', 'F150 SuperCrew Cab', 2016),
		(2, 'cptB1C1NSL', 'Chevrolet', 'Malibu', 2020),
		(3, '4q7L9FAU2S', 'Chrysler', 'Pacifica', 2019);


insert into carservicerest.categories(id, category_name)
values  (1,'Sedan'),
		(2,'SUV'),
		(3,'Pickup'),
		(4,'Minivan');

insert into carservicerest.car_category(car_id, category_id)
values  (1, 1),
		(3, 4),
		(2, 1);