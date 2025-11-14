class DynamicArray:
    def __init__(self):
        self._size = 0
        self._capacity = 1
        self._data = self._make_array(self._capacity)

    def _make_array(self, capacity):
        import ctypes
        return (capacity * ctypes.py_object)()

    def append(self, value):
        if self._size == self._capacity:
            self._resize(self._capacity * 2)
        self._data[self._size] = value
        self._size += 1

    def _resize(self, new_cap):
        new_data = self._make_array(new_cap)
        for i in range(self._size):
            new_data[i] = self._data[i]
        self._data = new_data
        self._capacity = new_cap

    def __getitem__(self, index):
        if 0 <= index < self._size:
            return self._data[index]
        raise IndexError("invalid index")

    def __setitem__(self, index, value):
        if 0 <= index < self._size:
            self._data[index] = value
            return
        raise IndexError("invalid index")

    def __len__(self):
        return self._size

    def pop(self):
        if self._size == 0:
            raise IndexError("pop from empty array")
        value = self._data[self._size - 1]
        self._size -= 1
        # optional shrink
        if self._size > 0 and self._size == self._capacity // 4:
            self._resize(self._capacity // 2)
        return value

    def insert(self, index, value):
        if index < 0 or index > self._size:
            raise IndexError("invalid index")
        if self._size == self._capacity:
            self._resize(self._capacity * 2)
        for i in range(self._size, index, -1):
            self._data[i] = self._data[i - 1]
        self._data[index] = value
        self._size += 1

    def __str__(self) -> str:
        return f"{self._data}"    

s = DynamicArray()
s.append(10)
print(s)
