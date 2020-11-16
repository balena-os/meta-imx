# Copyright (C) 2020 NXP
require optee-test.imx.inc

DEPENDS_append = " python3-pycryptodomex-native"

SRCBRANCH = "imx_5.4.70_2.3.0"
SRCREV = "0c998f42a3fb87b9f2929955cf4b0116cc515091"

do_compile_prepend () {
	export CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_HOST}"
}
