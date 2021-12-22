# We strongly recommend using the required_providers block to set the
# Azure Provider source and version being used
terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "=2.46.0"
    }
  }
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {}
}

# Create a resource group
resource "azurerm_resource_group" "xlabrg" {
  name     = "XmasLab2021DingsBums"
  location = "West Europe"
}

resource "azurerm_container_registry" "acr" {
  name                = "XmasLabCRDingsBums"
  resource_group_name = azurerm_resource_group.xlabrg.name
  location            = azurerm_resource_group.xlabrg.location
  sku                 = "Standard"
  admin_enabled       = false
}

resource "azurerm_kubernetes_cluster" "k8s" {
  name                = "XMasLab2021KS"
  location            = azurerm_resource_group.xlabrg.location
  resource_group_name = azurerm_resource_group.xlabrg.name
  dns_prefix          = "xmas"

  identity {
    type = "SystemAssigned"
  }

  default_node_pool {
    name       = "agentpool"
    node_count = 1
    vm_size    = "Standard_D2_v2"
  }

  network_profile {
    load_balancer_sku = "Standard"
    network_plugin    = "kubenet"
  }

  tags = {
    Environment = "Development"
  }
}