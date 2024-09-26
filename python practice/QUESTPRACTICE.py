import ctypes
import psutil

# 특정 프로세스 이름으로 프로세스 ID 얻기
def get_process_id(process_name):
    for proc in psutil.process_iter():
        if proc.name() == process_name:
            return proc.pid
    return None

# 메모리에서 값을 읽는 함수 (32비트 환경 대응)
def read_memory_value(process_handle, address):
    data = ctypes.c_int()  # 32비트 정수 사용 (4바이트)
    bytes_read = ctypes.c_size_t()  # 읽은 바이트 수
    result = ctypes.windll.kernel32.ReadProcessMemory(process_handle, address, ctypes.byref(data), ctypes.sizeof(data), ctypes.byref(bytes_read))
    
    if result == 0:  # ReadProcessMemory가 실패할 경우
        print(f"Failed to read memory at address: {hex(address)}")
        return None
    return data.value

# 베이스 주소를 얻는 함수 (Windows API 사용)
def get_base_address(pid, module_name="BubbleFighter.exe"):
    module_address = None
    hProcess = ctypes.windll.kernel32.OpenProcess(0x1F0FFF, False, pid)
    modules = (ctypes.c_ulong * 1024)()  # 32비트 정수 사용
    count = ctypes.c_ulong()
    
    if ctypes.windll.psapi.EnumProcessModules(hProcess, ctypes.byref(modules), ctypes.sizeof(modules), ctypes.byref(count)):
        for i in range(count.value // ctypes.sizeof(ctypes.c_ulong)):
            module_handle = modules[i]
            module_name_buffer = ctypes.create_string_buffer(1024)
            
            if ctypes.windll.psapi.GetModuleBaseNameA(hProcess, module_handle, ctypes.byref(module_name_buffer), ctypes.sizeof(module_name_buffer)):
                if module_name_buffer.value.decode() == module_name:
                    module_address = module_handle
                    break

    ctypes.windll.kernel32.CloseHandle(hProcess)
    return module_address

# 포인터 체인을 따라가며 최종 주소 얻기
def get_pointer_address(process_handle, base_address, offsets):
    current_address = base_address
    for offset in offsets:
        print(f"Current base address: {hex(current_address)}")
        current_value = read_memory_value(process_handle, current_address)
        
        if current_value is None:
            print(f"Failed to read memory at address: {hex(current_address)}")
            return None
        
        current_address = current_value + offset
        print(f"Next address after adding offset {hex(offset)}: {hex(current_address)}")
    
    return current_address

# 메모리 접근 및 값 수정 함수
def modify_memory_value(pid, base_address, offset_sets):
    PROCESS_ALL_ACCESS = 0x1F0FFF
    kernel32 = ctypes.windll.kernel32

    # 프로세스 열기
    process_handle = kernel32.OpenProcess(PROCESS_ALL_ACCESS, False, pid)

    if process_handle:
        # 각 포인터 체인에 대해 값을 수정
        for i, offsets in enumerate(offset_sets):
            print(f"\nProcessing chain {i+1}")
            # 포인터 체인을 따라 최종 주소 얻기
            final_address = get_pointer_address(process_handle, base_address, offsets)
            
            if final_address is None:
                print(f"[Chain {i+1}] Failed to resolve pointer chain.")
                continue
            
            # 최종 주소에서 현재 값 읽기
            current_value = read_memory_value(process_handle, final_address)
            if current_value is None:
                print(f"[Chain {i+1}] Failed to read memory at final address.")
                continue

            print(f"[Chain {i+1}] Memory value at address {hex(final_address)}: {current_value}")

            # 사용자가 입력한 새로운 값 받기
            try:
                new_value = int(input(f"Enter new value for Chain {i+1} (current value is {current_value}): "))
                
                # 새로운 값으로 수정
                new_data = ctypes.c_int(new_value)
                kernel32.WriteProcessMemory(process_handle, final_address, ctypes.byref(new_data), ctypes.sizeof(new_data), None)
                print(f"[Chain {i+1}] New value set at address {hex(final_address)}: {new_value}")
            except ValueError:
                print(f"Invalid input for Chain {i+1}, skipping...")

        # 프로세스 핸들 닫기
        kernel32.CloseHandle(process_handle)
    else:
        print("Failed to open process.")

# 예시 프로그램 이름 (예: "BubbleFighter.exe" 또는 수정하고 싶은 프로그램)
process_name = "BubbleFighter.exe"

# 베이스 메모리 주소 오프셋 ("BubbleFighter.exe" + 0x0149C90C)
offset = 0x0149C90C

# 첫 번째 포인터 체인
offsets_first = [0x18, 0x768, 0x4, 0x628]

# 나머지 포인터 체인들
offsets_second = [0x18, 0x76C, 0x4, 0x628]
offsets_third = [0x18, 0x770, 0x4, 0x628]
offsets_fourth = [0x18, 0x774, 0x4, 0x628]

# 오프셋 세트들
offset_sets = [
    offsets_first,   # 첫 번째 체인
    offsets_second,  # 두 번째 체인
    offsets_third,   # 세 번째 체인
    offsets_fourth   # 네 번째 체인
]

# 프로세스 ID 가져오기
pid = get_process_id(process_name)

if pid:
    # 베이스 주소 가져오기
    base_address = get_base_address(pid, process_name)
    if base_address:
        final_base_address = base_address + offset
        modify_memory_value(pid, final_base_address, offset_sets)
    else:
        print("Failed to get base address.")
else:
    print(f"Process {process_name} not found.")
