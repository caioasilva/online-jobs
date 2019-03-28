# Distributed Systems Group Project

Online Jobs Marketplace

## How to run

- Clone project
- Open in Netbeans
- Create a Derby Database with the following details:

| Field | Value |
| ------ | ------ |
| DB Name | onlinejobs |
| Username | jobsuser |
| Password | ds2019 |

- Copy the folder `onlinejobs` from `db_backup` to the location where Java DB (Derby) stores the databases in your computer
- Try to run the project, it will fail due to a bug in Netbeans while setting up Glassfish resources
- Access `http://localhost:4848`, go to Resources on the left menu and click on Add Resources Button
- Select the file `glassfish-resources.xml` that is locate in `online-jobs-ejb/src/conf` and click OK
- Stop the Glassfish Server running on Netbeans
- Compile and run the project again. It should work fine now

