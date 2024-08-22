import matplotlib.pyplot as plt
def DDA_simple_from_algorithm(x1, y1, x2, y2):
    # Step 1: Calculate the slope 'm'
    if x2 != x1:
        m = (y2 - y1) / (x2 - x1)
    else:
        m = float('inf')  # Vertical line case
    
    # Initialize the starting points
    x = x1
    y = y1
    
    # Lists to store points on the line
    x_points = [x1]
    y_points = [y1]
    
    # Step 2: Determine which case to use
    if abs(x2 - x1) >= abs(y2 - y1):  # Case 1: If |dx| >= |dy|
        dx = 1 if x2 > x1 else -1  # Move in the direction of x
        dy = m * dx  # Calculate dy based on the slope
        while x != x2:
            x += dx
            y += dy
            x_points.append(round(x))
            y_points.append(round(y))
    
    else:  # Case 2: If |dx| < |dy|
        dy = 1 if y2 > y1 else -1  # Move in the direction of y
        dx = dy / m if m != float('inf') else 0  # Calculate dx based on the slope
        while y != y2:
            x += dx
            y += dy
            x_points.append(round(x))
            y_points.append(round(y))
    
    # Special case: Handle vertical lines
    if m == float('inf'):
        x = x1
        y = y1
        while y != y2:
            y += 1 if y2 > y1 else -1
            x_points.append(x)
            y_points.append(y)
    
    # Plot the points
    plt.plot(x_points, y_points, 'ro-')
    plt.title("Simple DDA Line Drawing from Algorithm")
    plt.xlabel("X-axis")
    plt.ylabel("Y-axis")
    plt.grid(True)
    plt.show()

# Example usage
x1, y1 = 2, 3
x2, y2 = 7, 8

DDA_simple_from_algorithm(x1, y1, x2, y2)