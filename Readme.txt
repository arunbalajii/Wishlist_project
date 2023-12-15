This is wishlist module runs in port 9004


spring.data.mongodb.uri=mongodb+srv://${MONGODB_USERNAME}:${MONGODB_PASSWORD}@cluster0.g4gimeb.mongodb.net/login?retryWrites=true&w=majority
need to set environment varaibles in local and also in jenkins settings so that the details will be picked

in jenkins add both mongo and docker user/pass details

covers:

1. add product to wishlist
2. remove product
3. fetch list of items for particular user


Refer dockements in another git with name capstone_document