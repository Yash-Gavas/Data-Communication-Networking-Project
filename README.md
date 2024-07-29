# RoutePoisoningDetection

## Overview

This project simulates a network with routers implementing a simple routing protocol. It demonstrates a route poisoning attack, implements detection of tampered destination addresses, and includes basic mitigation techniques such as sending threat messages and blocking further packets from the attacker.

## Components

- **Network**: Simulates the network environment.
- **Router**: Represents a router with a routing table and implements detection of tampered routes.
- **Route**: Represents a route entry in the routing table.
- **RoutingTable**: Manages routing information for a router.
- **RoutePoisoningAttack**: Simulates a route poisoning attack.
- **MitigationTechniques**: Implements basic mitigation strategies like route filtering and trust management.
- **ThreatMessage**: Represents a threat message sent to a sender when tampering is detected.
- **Packet**: Represents a network packet with source, destination, and data.

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Open the project in VS Code.
4. Compile the Java files using `javac` in the terminal.
5. Run the `Main` class to see the simulation in action.

## Future Work

- Enhance the routing protocol with more features.
- Implement additional mitigation techniques.
- Add a graphical interface to visualize the network and routing tables.
