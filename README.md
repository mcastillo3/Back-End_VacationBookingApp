Using README.md for project notes.
Generated springboot and dependency files and imported them into D288 Back End project.
Created config, controllers, dao, entities, and services directories.
Imported RestDataConfig file into the config directory.
Imported application.properties into the resources directory.
Defined attributes for each entity class according to the UML table. Established relationships according to the ERD.
Created StatusType Enum class.
Created repository interfaces for each entity class, added JpaRepository, and CrossOrigin support.
Added RepositoryRestResource support to Country, Excursion, and Vacations interfaces.
Corrected @Table name path on Excursion entity.
Created a Purchase and PurchaseResponse class in the services directory.
Created a CheckoutService interface and a CheckoutServiceImpl class in the services directory.
Added a "convenience" method called add() to Customer and Cart entities.
Created a CheckoutController in controllers directory.
Moved Purchase and PurchaseResponse classes to a new directory called 'dto'.
Edited code in CheckoutController to add cartItems to cart and vica-versa.
Added code to CheckoutController to display a message for an empty cart.
