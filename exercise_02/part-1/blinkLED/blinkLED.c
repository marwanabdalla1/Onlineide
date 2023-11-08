
// This code is derived from the work done in the Raspberry-Pi Bare Metal Tutorials (see description below).

/*
    Part of the Raspberry-Pi Bare Metal Tutorials
    https://www.valvers.com/rpi/bare-metal/
    Copyright (c) 2013, Brian Sidebotham

    This software is licensed under the MIT License.
    Please see the LICENSE file included with this software.

*/

// delay function
void delay(int count) {
    while (count--) {
        // Simple delay loop (not precise, just for example)
        for (volatile int i = 0; i < 50000; i++);
    }
}

int main(void) __attribute__((naked));
int main(void)
{

    // TODO: define registers/addresses for LED functionality, LED output set

    // while(1)    // Never exit
    // {

    //     // TODO: code for blinking LED

    // }

    // Initialize GPIO pin for output
    GPIO_DIR |= LED_BIT;

    // Main loop
    while (1) {
        // Set the GPIO pin to high to turn on the LED
        GPIO_SET = LED_BIT;

        // Delay
        delay(1);

        // Set the GPIO pin to low to turn off the LED
        GPIO_CLR = LED_BIT;

        // Delay
        delay(1);
    }
    
}
