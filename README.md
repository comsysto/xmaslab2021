# Xmas Lab 2021
This application allows users to offer and order last-minute gifts for Christmas. The main use cases are:
- **Offer**: A user can offer an amount of gifts of the same type. The user specifies the location, price and amount of the offer.
- **Find**: A user can search for offers in her area (close to her location).
- **Order**: A user can order a gift from specific offer. The delivery location of the order has to be specified.

In this section, we describe the steps necessary to deploy this application to Azure. You need an Azure subscription and the following tools on your machine:
- **Azure CLI** to communicate with Azure through command line.
- **KubeCtl** To communicate with Kubernetes Cluster on Azure

Tools that might help for local testing:
- **Docker** for containerization
- **Minikube** for testing Kubernetes functions locally

## Deployment

To have this application running on a Kubernetes cluster on Azure, you have to create an image, upload it to a Container Registry on Azure, then deploy it to the Kubernetes cluster.

### Create Docker Image

To build a docker image named xmas (-t for tag) directly, run the following command form the application directory where the Dockerfile resides:

    ./gradlew bootBuildImage --imageName=xmas

Docker should have created an image for you.
If you go to Docker dashboard, you should be able to see the new image.

Now you can run the image from Docker dashboard or from command line
(where xmas is the name of the image):

    docker run -p 8080:8080 xmas

You should now be able to reach the application via http on the Url: http://localhost:8080/healthcheck

You can access the image's command line by running:

    docker run -ti --entrypoint /bin/sh xmas/xmas

To view image information:
    
    docker ps

To remove your image locally:

    docker rmi xmas

### Upload your image to an Azure Container Registry

You have first to create a container registry on Azure. Then you have to log in
to the registry from your command prompt (More [here](https://docs.microsoft.com/en-us/azure/container-registry/container-registry-get-started-docker-cli?tabs=azure-cli)):

    az login
    az acr login --name XMasContainerRegistry

Where XMasContainerRegistry is the name of your Container Registry.

#### Create an alias of the image
Use docker tag to create an alias of the image with the fully qualified path to your registry.

    docker tag xmas xmascontainerregistry.azurecr.io/xmas

Where xmas is the name you gave to your local image and the rest is the tag.


Now you can push your image to the container registry:

    docker push xmascontainerregistry.azurecr.io/xmas

When this is done, you should be able to see your image on Azure portal under your container registry.
You can also list existing images using CLI:

    az acr repository list --name XmasContainerRegistry

And to remove the image from the container registry:

    az acr repository delete --name XMasContainerRegistry --image xmas

#### Create an Azure Kubernetes Service
Before you create an Azure Kubernetes Service, you need a service principal for accessing it.
You can create the service principal with Azure CLI:
    
    az ad sp create-for-rbac --name XMasAKSClusterServicePrincipal

The output of the command should look like:

    {
        "appId": "c683d32e-e3c1-4157-b341-78f720655319",
        "displayName": "XMasAKSClusterServicePrincipal",
        "name": "c683d32e-e3c1-4157-b341-78f720655319",
        "password": "lVwk3~QeJUG4DVo-ctPy-dl2Sr~mDR0QbV",
        "tenant": "801baf61-cd8b-472d-879f-a96ce718a34d"
    }


Now you can create a Kubernetes Service on Azure Portal and assign this principal to it.
( In our Lab we didn't manage to assign this service principal)
### Deploy Application Image to AKS

You can ask KubeCtl to perform the deployment to your kubernetes cluster. You have to be logged in to Azure and to the Container registry (As described above), then you can run the following command:

    kubectl apply -f deployment.yaml

Create a service to expose the image on port 8080
    
    kubectl expose deployment xmas --type=LoadBalancer --port=8080


## This might be useful

To generate Kubernetes manifest file (usually deployment.yaml and service.yaml), you can run the following command:

    kubectl create deployment xmas --image=xmas:latest --dry-run=client -o=yaml > deployment.yaml
    kubectl create service xmas --image=xmas:latest --dry-run=client -o=yaml > deployment.yaml