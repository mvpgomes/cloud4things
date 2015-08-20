Event Latency Test Test
==========================

In order to test the latency of a event that occurs in a physical space we are simulating a scenario where the ALE server is connected to a reader that capture a collection of RFID tags.

Since that it was not possible to simulate the scenario with physical reader, in this evaluation we use [RifidiEmulator](http://www.transcends.co/community), an emulator that allows to simulate a variety of readers - including the LLRP that is supported by Fosstrak.

The ``cloudfied_configuration`` and ``edge_configuration`` directories contains the results of the latency tests for the hybrid and cloud-based configuration of the ALE server. The ``logs`` directory contains the generated logs by the Fosstrak and by the ``tcpdump`` command-line tool that was used to monitoring the network connection of the test environment.

In our Wiki page we will present a more detailed evaluation of the obtained performance results.
